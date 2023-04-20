(ns prueba-clj-03.sc.schema_core_examples
  (:require [schema.core :as s]
    [clojure.test :refer :all]
            )
)
;(load "../../test/prueba_clj_03/sc/schema_core_examples")
;(use prueba-clj-03.sc.schema_core_examples)

(s/defrecord Persona
             [dato1 :- long
              dato2 :- String])

(s/defn formula :- long
        [miPersona :- Persona
         factor :- long]
        (* factor (+ (:dato1 miPersona) (Long/parseLong (:dato2 miPersona)))))

(s/defschema PersonaSchema
  {
   :age s/Num
  }
)


(s/defn makeItOlder :- long
  [miPersona :- PersonaSchema
   factor :- long]
  (* factor (:age miPersona))
)



(deftest my_test_about_schemas
  (testing "t1" (is (= (formula (Persona. 10 "5") 2) 30)))

  (def charly {:age 20})
  (testing "tolder" (is (= (makeItOlder charly 2) 40)))
  (assoc charly :age 30)
  (testing "tolder2" (is (= (makeItOlder charly 2) 60)))
)

(defn enhanceCharly [charly]
  (def charly2 (assoc charly :age 30 ))
  charly2
)


(deftest my_test_about_schemas2
  (def charly {:age 20})
  (testing "enhance charly" (is (= (:age (enhanceCharly charly)) 30)))
  (testing "tolder2" (is (= (makeItOlder (enhanceCharly charly) 2) 60)))
)