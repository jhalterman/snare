(ns snare.util
  (:use clojure.set))

(defn has-keys? [m keys]
  "Returns whether m contains all of the keys"
  (every? (partial contains? m) keys))

(defn key-diff [m keySet] 
  "Returns the keys in m that are not present in keySet"
  (difference (set keySet) (set (keys m))))