(defproject org.jodah/snare "0.1.0-SNAPSHOT"
  :description "For snaring rabbits and such"
  :url "https://github.com/jhalterman/snare"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.rabbitmq/amqp-client "3.2.1"]
                 [org.clojure/tools.cli "0.2.4"]]
  :uberjar-name "snare.jar"
  :main snare.core)