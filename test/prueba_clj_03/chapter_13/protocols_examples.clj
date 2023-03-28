(ns prueba-clj-03.chapter-13.protocols_examples
  (:require [clojure.test :refer :all]))
(defprotocol p2
  "Plumb the inner depths of your data types"
  (a1 [x] "The data type's innermost thoughts")
  (a2 [x] [x y] "Feelings about self or other"))

(extend-protocol p2
  java.lang.String
  (a1 [x] "Truly, the character defines the data type")
  (a2
    ([x] "longing for a simpler way of life")
    ([x y] (str "envious of " y "'s simpler way of life")))

  java.lang.Object
  (a1 [x] "Maybe the Internet is just a vector for toxoplasmosis")
  (a2
    ([x] "meh")
    ([x y] (str "meh about " y))))


(deftest p2_test
  (testing "t1" (is (= (a1 "pepe") "Truly, the character defines the data type" )))
  (testing "t2" (is (= (a2 "juan" 2)  "envious of 2's simpler way of life" )))
  (testing "t3" (is (= (a1 ["juan"] )  "Maybe the Internet is just a vector for toxoplasmosis"  )))
  (testing "t4" (is (= (a2 ["juan"] )  "meh"  )))
  (testing "t5" (is (= (a2 ["juan"] 2 )  "meh about 2"  )))
)