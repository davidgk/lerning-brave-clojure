(ns prueba-clj-03.example01-test
  (:require [clojure.test :refer :all])
  (:require [prueba-clj-03.example01 :refer [error-message]]))

(deftest error-message-test
  (testing "should message"
    (is (= (error-message :mild) "OH GOD! IT'S A DISASTER! WE'RE MILDLY INCONVENIENCED!"))
    )
)

(deftest retrieve_map_text
  (testing "t1" (is (= (:p1 {:p1 1 :p2 2}) 1)))
  (testing "t2" (is (= (get-in {:p1 {:p3 3} :p2 2} [:p1 :p3]) 3)))
  (testing "t3" (is (= (:p5 {:p1 {:p3 3} :p2 2} "not there") "not there")))
  (testing "t4" (is (=  ({:p1 {:p3 3} :p2 2} :p2) 2)))
  (def x (let [my_map {:p1 {:p3 3} :p2 2}]
    my_map))
  (testing "t5" (is (=  (x :p2) 2)))
  (testing "t5" (is (=  (x :p5) nil)))
)
