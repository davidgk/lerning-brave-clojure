(ns prueba-clj-03.data_structures-test
  (:require [clojure.test :refer :all])
  (:require [prueba-clj-03.data_structures :refer [error-message]]))

(deftest error-message-test
  (testing "should message"
    (is (= (error-message :mild) "OH GOD! IT'S A DISASTER! WE'RE MILDLY INCONVENIENCED!"))
    )
)

(deftest maps
  (testing "t1" (is (= (:p1 {:p1 1 :p2 2}) 1)))
  (testing "t2" (is (= (get-in {:p1 {:p3 3} :p2 2} [:p1 :p3]) 3)))
  (testing "t3" (is (= (:p5 {:p1 {:p3 3} :p2 2} "not there") "not there")))
  (testing "t4" (is (=  ({:p1 {:p3 3} :p2 2} :p2) 2)))
  (def x (let [my_map {:p1 {:p3 3} :p2 2}]
    my_map))
  (testing "t5" (is (=  (x :p2) 2)))
  (testing "t6" (is (=  (x :p5) nil)))
  (def david {:name "david" :age 47})
  (testing "t7" (is (=  (david :name) "david")))
)

(deftest vectors
  (def my_vector ["grisi" "catalina" "lourdes" "cecilia"])
  (testing "t1" (is (= (get my_vector 0) "grisi")))
  (testing "t2" (is (= (my_vector 0) "grisi")))
  (testing "t3" (is (= (my_vector 3) "cecilia")))
  (testing "t3" (is (=  ((conj my_vector "titi") 4) "titi")))
  (def vector_map ["cata" {:p1 "carlos"} "juan"])
  (testing "t4" (is (=  (:p1 (vector_map 1)) "carlos")))
  (deftest other_vectors
    (def my_vector ["grisi" "catalina" "lourdes" "cecilia"])
  )
)
(deftest lists
  (def my_list '("carlos" "juan" "lisa"))
  (testing "t1" (is (= (nth my_list 1) "juan")))
  (def list_01 (list 1 "two" {:p3 5 :other 8}))
  (testing "t2" (is (= ( nth list_01 1) "two")))
  (testing "cualquier cosa en una lista" (is (=  (:p3 ( nth list_01 2)) 5)))
  (testing "t3" (is (=  (nth (conj list_01 "titi") 0) "titi")))
)

(deftest hash_set
  (def my_hash_set #{ "carlos" "juan" "lisa" })
  (testing "t1" (is (= (get my_hash_set "carlos") "carlos")))
  (testing "t2" (is (= (get my_hash_set "pedro") nil)))
  (testing "t3" (is (= (contains? my_hash_set "pedro") false)))
  (testing "t4" (is (= (contains? my_hash_set "juan") true)))
  (testing "t5" (is (= (set [1 1 1 2 3 3 4 4 4]) #{ 1 2 3 4})))

  )