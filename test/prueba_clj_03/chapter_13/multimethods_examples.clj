(ns prueba-clj-03.chapter-13.multimethods_examples
  (:require [clojure.test :refer :all]))

(defmulti full-moon-behavior (fn [were-creature] (:were-type were-creature)))
(defmethod full-moon-behavior :wolf
    [were-creature]
    (str (:name were-creature) " will howl and murder"))
(defmethod full-moon-behavior :simmons
    [were-creature]
    (str (:name were-creature) " will encourage people"))

(defmethod full-moon-behavior nil
  [were-creature]
  (str (:name were-creature) " entonces no hace un carajo"))

(defmethod full-moon-behavior :default
  [were-creature]
  (str (:name were-creature) " no tiene nada que ver"))


(deftest full-moon-behavior_test
  (let [wolfi (full-moon-behavior {:were-type :wolf :name "Rachel" })]
    (is (= wolfi "Rachel will howl and murder")))

  (def simmons (full-moon-behavior {:were-type :simmons :name "Carlos" }))
  (is (= simmons "Carlos will encourage people"))

  (def whenNil (full-moon-behavior {:were-type nil :name "Juan" }))
  (is (= whenNil "Juan entonces no hace un carajo"))
  (def whenDefault (full-moon-behavior {:were-type :otra_cosa :name "David" }))
  (is (= whenDefault "David no tiene nada que ver"))
)
(defn adding [a b ] (+ a b))
(deftest adding-test
  ;"aca defino la cantidad de parametros a ingresar"
  (are [expr-expected expresion-obtained]
    ; aca defino que es lo que se va a evaluar.
    (= expr-expected expresion-obtained)
      5 (adding 3 2)
      10 (adding 6 5)
      10 (adding 3 7)
    ))
(deftest check-are-with-maps
  (are [result arg-map] (= result (+ (:x arg-map) (:y arg-map)))
                        5      {:x 2 :y 3},
                        10     {:x 6 :y 4})
  (are [result arg-map_01 arg-map_02] (= result (+ (:x arg-map_01) (:x arg-map_02)))
                        5      {:x 2 } { :x 3},
                        10     {:x 4 } { :x 6}))

;; La idea es que multimetod define la funcion seleccionadora
; 1.. recibe un map.. y el parametro que recibe devuelve el tipo de metodo que va a usar..
; 2.. el defmethod dice a que familia pertenese y cual es la logica que va a tomar de ese metodo original
; 3. tambien recibe como parametro la estructura de datos y en funcion de eso toma algo de la estructura de datos y le argega una frase
; Es el patron estrategy .. pero con mucha mas abstraccion.


