(ns prueba-clj-03.chapter-7.learning-macros
  (:require [clojure.test :refer :all]))

(defmacro infix
  "Use this macro when you pine for the notation of your childhood"
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))
(defmacro infix-2
  "Use this macro when you pine for the notation of your childhood"
  [[a b c]]
  (list b a c)
  )

(defmacro Simple [] '(println "Hello"))
(defmacro Simple2 [] '(+ 1 1))
(defmacro Simple3 [] `(+ 1 ~(+ 1 5)))
(deftest macros_tests
  (testing "t1" (is (= (infix (1 + 1)) 2)))
  (testing "t2" (is (= (macroexpand '(infix (1 + 1))) '(infix (1 + 1)))))
  (testing "t21" (is (= (macroexpand '(infix-2 (1 + 1))) '(infix-2 (1 + 1)))))
  (testing "t22" (is (= (macroexpand `(infix-2 (1 + 1))) '(clojure.core/+ 1 1))))
  (testing "t3" (is (= (infix-2 (1 + 1)) 2)))
  (testing "t3" (is (= (Simple) nil)))
  (testing "t31" (is (= (macroexpand '(Simple)) '(Simple))))
  (testing "t32" (is (= (macroexpand `(Simple)) '(println "Hello"))))
  ; Simple2
  (testing "t4" (is (= (Simple2) 2)))
  (testing "t41" (is (= (macroexpand '(Simple2)) '(Simple2))))
  (testing "t42" (is (= (macroexpand `(Simple2)) '(+ 1 1))))
  ; Simple3
  (testing "t5" (is (= (Simple3) 7)))
  (testing "t51" (is (= (macroexpand '(Simple3)) '(Simple3))))
  (testing "t52" (is (= (macroexpand `(Simple3)) '(clojure.core/+ 1 6))))
  )

(defn sumar [a b] (+ a b))
(defmacro apilarSuma [a b] (list 'sumar (sumar a b) b))
(defmacro apilarSuma2 [a b] `(sumar (sumar ~a ~b) ~b))


(deftest apilarSumatest
  (testing "t1" (is (= (apilarSuma 2 2) 6)))
  (testing "t2" (is (= (apilarSuma2 2 2) 6)))
  )

(defn criticize-code [criticism code] `(str ~criticism (quote ~code)))
(defn ap [criticism code] `(str ~criticism (quote ~code)))

(deftest criticize-code-test
  (testing "t1" (is (= (apply criticize-code "carlos" (+ 1 1)) "pepe")))
  )

;(-> (/ 144 12) (/ ,,, 2 3) str keyword list)
(deftest macros_as_arrow_01
  (let [a (-> (/ 144 12) (* 3))]
    (testing "a1" (is (= a 36))))
  (let [a (-> (/ 144 12) (* 3) str)]
    (testing "a2" (is (= a "36"))))
  (let [a (-> (/ 144 12) (* 3) str list)]
    (testing "a2" (is (= a `("36")))))
  )


(defn suma3 [x]
  (+ x 3))
(defn suma5 [x]
  (+ x 5))
(defn multi2 [x]
  (* x 2))

(deftest test_->>
  (is (= (->> (suma3 2) suma5 multi2) 20)))


(deftest arrow_test
  (is (= (->> (suma3 2) multi2 suma5) 15))
  (is (= (-> (suma3 2) multi2 suma5) 15))
  (is (= (-> (suma3 2) ((fn [x y] (+ x y)) 20) suma5) 30))
  (def carlos {:name "carlos" :data {:age 20 :address "Sarmiento"}})
  (is (= (-> carlos :data :address) "Sarmiento"))
  (is (= (->> carlos :data :address) "Sarmiento"))
  )