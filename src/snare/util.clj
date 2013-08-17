(ns snare.util
  (:require clojure.java.io)
  (:use clojure.set))

(defn has-keys? [m keys]
  "Returns whether m contains all of the keys"
  (every? (partial contains? m) keys))

(defn key-diff [m keySet] 
  "Returns the keys in m that are not present in keySet"
  (difference (set keySet) (set (keys m))))

(defn load-props
  [file-name]
  (with-open [^java.io.Reader reader (clojure.java.io/reader file-name)]
    (let [props (java.util.Properties.)]
      (.load props reader)
      (into {} (for [[k v] props] [(keyword k) v])))))