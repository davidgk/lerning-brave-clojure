(ns prueba-clj-03.functions-test
  (:require [clojure.test :refer :all]))


(deftest function_test_01
  (testing "t1" (is (= ((or + -) 1 2 3) 6)))
)

(def afecto "quiero")
(defn amar [x] (str x " te " afecto ))

(deftest test_amar
  (testing "t1" (is (= (amar "lourdes") (str "lourdes te " afecto) )))
  (testing "t2" (is (= (map amar ["lourdes" "cata" "gaby"]) ["lourdes te quiero" "cata te quiero" "gaby te quiero"] )))
  (testing "t2" (is (= (map amar ["lourdes" "cata" "gaby"]) '("lourdes te quiero" "cata te quiero" "gaby te quiero") )))
  (testing "t2" (is (= (map amar '("lourdes" "cata" "gaby")) '("lourdes te quiero" "cata te quiero" "gaby te quiero") )))
)

(defn addition-on-demand
  ([x] (+ x 1))
  ([x y] (+ x y 100))
  ([x y z] (+ x y z 1000))
)
(deftest checking_multiarity
  (testing "t1" (is (=   (addition-on-demand 1)  2)))
  (testing "t2" (is (=   (addition-on-demand 1 5)  106)))
  (testing "t3" (is (=   (addition-on-demand 1 5 10)  1016)))
)


(defn isNice [name] (str name " is nice"))
(defn beauty [& names] (map isNice names) )

(deftest check_beauty
  (testing "t1" (is (= (nth (beauty "gaby" "cata" "luly") 0) "gaby is nice" )))
  (testing "t2" (is (= (nth (beauty "gaby" "cata" "luly") 1) "cata is nice" )))
  (testing "t3" (is (= (nth (beauty "gaby" "cata" "luly") 2) "luly is nice" )))
)
; #########################
; LAMBDAS
; #########################
(deftest name
  (is (= (#(str %1 " " %2) "sapo" "pepe" ) "sapo pepe"))
  (is (= (#(str % " campeon") "sapo" ) "sapo campeon"))
  (is (= (map #(str "Hi, " % )  ["sapo", "rana"]  ) ["Hi, sapo" "Hi, rana"]))
)



; #########################
; LET
; #########################
(def x 5)
(def z (let [x 10] (inc x)))
(deftest test_01
  (is (= x 5))
  (is (= z 11))
  (is (= x 5)  )
  )