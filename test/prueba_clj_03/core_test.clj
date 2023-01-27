(ns prueba-clj-03.core-test
  (:require [clojure.test :refer :all]
            [prueba-clj-03.core :refer :all]))


(def a (into [] (set [1 1 2 2 3 3 3 4 5])))
(deftest a-test
  (is (= a [1 4 3 2 5]))
  )

(defn incra [x] (inc x))
(defn make-great-list [size] (for [i (range size)] (incra i)))

(deftest a-test-01
  (is (= (make-great-list 1) '(1)))
  (is (= (make-great-list 5) '(1 2 3 4 5)))
  )

(def names ["carlos" "juan" "adrian"])

(defn job [name] (str name " is a worker"))

(defn evaluate-job [names] (for [aName names] (job aName )))

(deftest a-test-02
  (is (= (evaluate-job names) '("carlos is a worker" "juan is a worker" "adrian is a worker")))
  (is (= (map job names) '("carlos is a worker" "juan is a worker" "adrian is a worker")))
)

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)
   }
)

(deftest a-test-03 "using regex"
  (testing "t1" (is (= (clojure.string/split "david" #"") '("d" "a" "v" "i" "d")  )))
  (testing "t2" (is (= (clojure.string/replace "carolete" #"ete" "ina") "carolina"   )))
  (testing "t3" (is (= (matching-part {:name "left-eye" :size 1}) {:name "right-eye" :size 1}    )))
  (testing "t3" (is (= (matching-part {:name "head" :size 3}) {:name "head" :size 3}    )))
)
