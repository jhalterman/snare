(ns snare.util-test
  (:use clojure.test
        snare.util))

(deftest key-diff-test
  (is (= 
        (key-diff {:a 1 :b 2} [:a :b :c :d])
        #{:c :d}))
  (is (=
        (key-diff {} [])
        #{}))
  (is (=
        (key-diff {:a 1 :b 2} [:a])
        #{})))

(deftest has-keys-test
  (is (= 
        (has-keys? {:a 1 :b 2} [:a :b])
        true))
  (is (=
        (has-keys? {:a 1 :b 2} [:a :b :c])
        false)))
