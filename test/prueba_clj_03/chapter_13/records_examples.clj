(ns prueba-clj-03.chapter-13.records_examples
  (:require [clojure.test :refer :all]))
(defrecord Persona [name age])

(defprotocol Programador
  (desarrolloSw [x] "desarrolla software")
  (desarrolloParm [x y] "desarrolla software")
)

(defrecord Trabajador [nombre legago]
  Programador
  (desarrolloSw [x] (str nombre " desarrolla en java"))
  (desarrolloParm [x, y] (str nombre " desarrolla en " y))
)


(deftest TrabajadorTest
  (def pepito (Trabajador. "carlos" "12345"))
  (testing "t1" (is (= (:nombre pepito) "carlos" )))
  (testing "t1" (is (= (desarrolloSw pepito) "carlos desarrolla en java" )))
  (testing "t1" (is (= (desarrolloParm pepito "python") "carlos desarronlla en python" )))
)


(deftest Persona_test
  (def david (->Persona "David" 47))
  (testing "t1" (is (= (:name david) "David" )))
  (testing "t1" (is (= (.name david) "David" )))
  (testing "t1" (is (= (get david :name) "David" )))
  (testing "t11" (is (= (:age david) 47 )))
  (def juan (Persona. "Juan" 74))
  (testing "t2" (is (= (:name juan) "Juan" )))
  (testing "t21" (is (= (:age juan) 74 )))

  (def mariaMap {:name "Maria" :age 20})
  (def maria (map->Persona mariaMap))
  (testing "t3" (is (= (:name maria) "Maria" )))
  (testing "t31" (is (= (:age maria) 20 )))

  (testing "t4" (is (= david (->Persona "David" 47) )))
  (testing "t41" (is (=  (= david (->Persona "David" 48)) false )))
  (testing "t42" (is (=  (= maria {:name "Maria" :age 20}) false ))) ; different type

  (assoc maria :age 25)
  (testing "t5" (= (:age maria) 25))
)