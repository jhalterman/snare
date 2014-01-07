(ns snare.core
  (:gen-class)
  (:use [clojure.tools.cli :only (cli)]
        clojure.set
        snare.util)
  (:import [com.rabbitmq.client ConnectionFactory DefaultConsumer]
           [java.io ByteArrayInputStream DataInputStream]))

(defn- read-opts [args]
  (let [file (:file args)]
    (if file
      (merge args (load-props file))
      args)))

(defn- consume [args]
  (let [cxn-factory (doto (ConnectionFactory.)
                      (.setHost (:host args))
                      (.setPort (:port args))
                      (.setUsername (:username args))
                      (.setPassword (:password args))
                      (.setVirtualHost (:vhost args)))
        connection (.newConnection cxn-factory)
        channel (.createChannel connection)
        consumer (proxy [DefaultConsumer] [channel]
                   (handleDelivery [consumerTag envelope properties body]
                     (println (String. body))))]
    (.basicConsume channel (:queue args) true consumer)
    (println "Connected...")))

(defn -main [& args]
  (let [[opts args banner]
        (cli args
             ["-f" "--file" "Properties file"]
             ["-h" "--host" "Host"]
             ["--port" "Port" :default 5672 :parse-fn #(Integer/parseInt %)]
             ["-u" "--username" "Username"]
             ["-p" "--password" "Password"]
             ["-v" "--vhost" "Vhost" :default "/"]
             ["-r" "--routing-key" "Routing key to bind to queue" :default "#"]
             ["-q" "--queue" "Name of queue to consume from"])
        opts (read-opts opts)
        missing-opts (key-diff opts [:host :port :vhost :username :password :routing-key :queue])]
    (if (empty? missing-opts)
      (consume opts)
      (do
        (print "Missing args: ")
        (apply println (map name missing-opts))
        (println banner)))))