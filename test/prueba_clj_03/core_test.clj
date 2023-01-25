(ns prueba-clj-03.core-test
  (:require [clojure.test :refer :all]
            [prueba-clj-03.core :refer :all]))


(defn first_if [x]
  (if (>= x 2)
    (str "over 2")
    (str "less 2")
  )
)



(deftest check_first_if
  (def sut 5)
  (testing "should return over 2 if value is major than 2"
    (is (= (first_if sut) "over 2"))
  )
  (def sut 2)
  (testing "should return over 2 if value is equal than 2"
    (is (= (first_if sut) "over 2"))
  )
  (def sut 1)
  (testing "should return less 1 if value is less than 2"
    (is (= (first_if sut) "less 2"))
  )
)

