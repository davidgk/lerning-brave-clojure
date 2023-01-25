(ns prueba-clj-03.do_when_examples_tests
  (:require [clojure.test :refer :all]
  [prueba-clj-03.do_when_examples :refer :all]
))


(deftest check_def_do
  (def sut "ramon")
  (testing "should return ramon ahi vamos when we send ramon"
    (is (= (def_do sut) "ramon ahi vamos"))
  )
)

(deftest check_def_when
  (testing "should return hola mundo, ramon"
    (is (= (def_when "hola" "ramon") "hola mundo, ramon"))
  )

  (testing "should return nil"
    (is (= (def_when "otra cosa" "ramon") "No saludamos"))
  )
)