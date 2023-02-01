(ns prueba-clj-03.chapter-3.partial-comp-memoize-ex
  (:require [clojure.test :refer :all]))

;##################################
; PARTIAL

(defn  accion_retarda [level message]
  (condp = level
    :levantarse (str "lavarse los dientes y " message)
    :almorzar (str "comer y " message)
    )
  )

(def wakeUp  (partial accion_retarda :levantarse))

(deftest one_dia_test
  (testing "t1" (is (= (wakeUp "la cara") "lavarse los dientes y la cara" )))
)

(def movilidad
  {
   :correr (fn [patas] (str "corremos con " patas))
  }
)

(def correr_animal (partial (movilidad :correr) ))

(def animal
  {
    :name "tigre"
    :patas 2
  }
)

(deftest check_tigre
  (testing "name" (is (= (animal :name) "tigre" )))
  (testing "correr" (is (= (correr_animal  (animal :patas) ) "corremos con 2"  )))
)

;##################################
; COMP

(def Persona {
      :actions {
        :walk #(str "Yo camino escuchando " %  )
      }
     :extremidades {
                    :piernas 2
                    :brazos 2
                    }

     :corazon 1
     :caminar #(str "Yo camino con " % " piernas" )
     :caminar2 (fn [val] (str "Yo camino con " val " piernas")  )
     }
  )

(def cant_piernas (comp :piernas :extremidades))
(def action_walk (comp :walk :actions))

(deftest persona_camina
  (is (= (cant_piernas Persona) 2))
  (testing "persona Camina 1" (is (= ((Persona :caminar) 2) "Yo camino con 2 piernas" )))
  (testing "persona Camina 2" (is (= ((Persona :caminar2) (cant_piernas Persona)) "Yo camino con 2 piernas" )))
  (testing "persona walk" (is (= ((action_walk Persona) "Mozart") "Yo camino escuchando Mozart" )))
)

(defn two-comp
  [f1 g1]
  (fn [& args]
    (f1 (apply g1 args))))

(defn suma_1 [val] (+ 5 val ) )
(defn suma_2 [val] (+ 10 val) )

(deftest evaluate_2comp
  (testing (is (= (suma_1 5) 10)))
  (testing (is (= (suma_2 5) 15)))
  (testing (is (= (suma_1 (suma_2 5))  20)))
  (testing (is (= ((two-comp suma_1 suma_2) 5)  20)))
  ;(testing "t1" (is (= () 35)))
)

(defn f [x] (+ 11 (- x 90)))
(defn g [x] (* 2 x))

;; Use comp to create a new function based
;; on functions f and g.
;; Function are applied from right to left,
;; so first f and then g.
(is (= 42 ((comp g f) 100)))

(deftest t1test
  (testing "t1" (is (= 42 ((comp g f) 100))))
)
