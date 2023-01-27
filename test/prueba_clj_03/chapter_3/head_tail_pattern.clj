(ns prueba-clj-03.chapter_3.head-tail-pattern
  (:require [clojure.test :refer :all]))


(def lista_ht_01 ["uno" "dos" "tres" "cuatro"])



(defn encola-perro  "adding word perro a cada palabra en la lista"
  [aParam]
  (for [word aParam]
    (str word "-perro")
  )
)

(deftest test-01
  (testing "t1" (is (= (encola-perro '("uno")) ["uno-perro"] ) ))
  (testing "t1" (is (= (encola-perro '("uno")) ["uno-perro"] ) ))
)

(defn add-perro [dest word] (conj dest (str word "-perro")))
(defn using-reduce [aList] (reduce add-perro [] aList))

(deftest reduce_testing
  (is (= (using-reduce '("a" "b" "c")) '("a-perro" "b-perro" "c-perro")))
  )


(defn myReduce [] (reduce
             (fn [dest ,val] (conj dest (str val "-perro")) )
             []
             ["a" "b" "c"]))
(deftest red2
  (is (= (myReduce) ["a-perro" "b-perro" "c-perro"]))
  )