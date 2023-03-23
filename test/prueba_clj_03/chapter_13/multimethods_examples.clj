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
  (def wolfi (full-moon-behavior {:were-type :wolf :name "Rachel" }))
  (is (= wolfi "Rachel will howl and murder"))
  (def simmons (full-moon-behavior {:were-type :simmons :name "Carlos" }))
  (is (= simmons "Carlos will encourage people"))

  (def whenNil (full-moon-behavior {:were-type nil :name "Juan" }))
  (is (= whenNil "Juan entonces no hace un carajo"))
  (def whenDefault (full-moon-behavior {:were-type :otra_cosa :name "David" }))
  (is (= whenDefault "David no tiene nada que ver"))
)

;; La idea es que multimetod define la funcion seleccionadora
; 1.. recibe un map.. y el parametro que recibe devuelve el tipo de metodo que va a usar..
; 2.. el defmethod dice a que familia pertenese y cual es la logica que va a tomar de ese metodo original
; 3. tambien recibe como parametro la estructura de datos y en funcion de eso toma algo de la estructura de datos y le argega una frase
; Es el patron estrategy .. pero con mucha mas abstraccion.


