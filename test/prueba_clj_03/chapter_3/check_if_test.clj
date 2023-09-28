(ns prueba-clj-03.chapter_3.check_if_test
  (:require [clojure.test :refer :all]
            [prueba-clj-03.chapter_3.check_if :refer :all]
  )
)

(deftest check_def_if
  (def sut 5)
  (testing "should return holly_shirt if value is major than 2"
    (is (= (def_if sut) :holly_shirt))
    )
  (def sut 1)
  (testing "should return nil if value is equal than 2"
    (is (= (def_if sut) nil))
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

(deftest check_scnd_if
  (def sut 5)
  (testing "should return over 2 if value is major than 2"
    (is (= (scnd_if sut) "over 2"))
  )
  (def sut 2)
  (testing "should return over 2 if value is equal than 2"
    (is (= (scnd_if sut) "over 2"))
  )
  (def sut 1)
  (testing "should return less 1 if value is less than 2"
    (is (= (scnd_if sut) "less 2"))
  )
)


(deftest check_third_if
  (testing "t1"
    (is (= (third_if 5) "valid"))
  )
)



(defn using_when [a b]
  (when (= a b)
    "Carlos"
  )
)


(deftest understanding_when
  (is (=  (using_when 1 1) "Carlos"))
  (is (=  (using_when 1 3) 5))
  )

(deftest check_merge
   (let [
         map_a { :tito "yo soy tito"}
         ]
    (testing "t1" (is (= (merge map_a {:carlos "yo soy carlos"}) {:tito "yo soy tito" :carlos "yo soy carlos"} )))
    (testing "t2" (is (= (:tito map_a) "yo soy tito" ))))
    (testing "t3" (is (= (if (:pepe map_a) ("empty") ("sth")) "empty" )))
)
