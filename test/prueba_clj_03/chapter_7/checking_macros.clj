(ns prueba-clj-03.chapter-7.checking-macros
  (:require [clojure.test :refer :all]))


(defn sumar3 [x] (+ x 3))
(defn sumar5 [x] (+ x 5))

(defn sumarDefault [x] (+ x 7))

(def value 5)

(defmacro miMacro [funA funB flag]
  (if (> flag 20)
    funA
    funB
  )
)

;(defn action [x, y] ((miMacro sumar3 sumar5 (sumarDefault y))) x)
(deftest evaluate_a_macro
  (is (= value 5))
  (is (= (sumar3 5) 8))
  (is (= (sumar5 5) 10))
  (is (= (sumarDefault 5) 12))
  (is (= (miMacro (sumar3 5) (sumar5 10) 25) 8))
  (is (= (macroexpand '(miMacro (sumar3 5) (sumar5 10) 25)) '(miMacro (sumar3 5) (sumar5 10) 25)))
  (is (= (eval (macroexpand (miMacro (sumar3 5) (sumar5 10) 25)))  8 ))
  (def actiona (sumar3 5))
  (def actionb (sumar3 10))
  (is (= (miMacro actiona actionb 25) 8))



)


