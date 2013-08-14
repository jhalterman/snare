(ns snare.core
  (:gen-class)
  (:use [clojure.tools.cli :only (cli)]
        clojure.set
        snare.util)
  (:require [clj-yaml.core :as yaml])
  (:import [com.rabbitmq.client ConnectionFactory QueueingConsumer]))

(declare consume)

(defn -main [& args]
  (let [[opts args banner]
        (cli args
             ["-h" "--host" "Host"]
             ["--port" "Port" :default 5672 :parse-fn #(Integer/parseInt %)]
             ["-u" "--username" "Username"]
             ["-p" "--password" "Password"]
             ["-v" "--vhost" "Vhost" :default "/"]
             ["-r" "--routingKey" "Routing key to bind to queue" :default "#"]
             ["-q" "--queue" "Name of queue to consume from"])
        missingOpts (key-diff opts [:host :port :vhost :username :password :routingKey :queue])]
    (if (empty? missingOpts)
      (consume opts)
      (do
        (print "Missing args: ")
        (apply println (map name missingOpts))
        (println banner)))))

(defn consume [args]
  (let
    [cxnFactory (doto (ConnectionFactory.)
                             (.setHost (:host args))
                             (.setPort (:port args))
                             (.setUsername (:username args))
                             (.setPassword (:password args))
                             (.setVirtualHost (:vhost args)))
     connection (. cxnFactory newConnection)
     connection (. cxnFactory newConnection)
     channel (. connection createChannel)
     consumer (QueueingConsumer. channel)]
    (.basicConsume channel (:queue args) true consumer)
    (println "Connected...") 
    (loop []
      (let [delivery (. consumer nextDelivery)
            body (String. (. delivery getBody))]
        (println body)
        (recur)))))