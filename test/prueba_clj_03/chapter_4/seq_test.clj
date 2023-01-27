(ns prueba-clj-03.chapter-4.seq-test
  (:require [clojure.test :refer :all]))


(def my_map {:uno 1 :dos 2 :tres 3})
(defn to_seq [aVal] (seq aVal))
(defn to_map [aVal] (into {} aVal))
(deftest check_seq
  (testing "my map" (is (= (my_map :uno) 1  )))
  (testing "seq it" (is (= (nth (to_seq my_map) 0) [:uno 1]  )))
  (testing "againstMap" (is (= ((to_map(to_seq my_map)) :dos) 2  )))
)


(def duracion '("1hr" "2hr" "3hr"))
(def backlog '("my_feature" "my_feature01" "my_feature02"))
(def recursos '("david" "carlos" "julian"))

(defn create_project [tiempo alcance recurso]
  {:tiempo tiempo :alcance alcance :recurs recurso}
)
(defn create_list_projects []
  (map create_project duracion backlog recursos)
)
(deftest test-unify-lists
  (testing "create_project" (is (= (create_project "1hr" "my_feature" "1dev") {:tiempo "1hr" :alcance "my_feature" :recurs "1dev"} )))
  (testing "list_projects_size" (is (= (count (create_list_projects)) 3  )))
  (testing "list_projects_content" (is (= (nth (create_list_projects) 0) {:tiempo "1hr" :alcance "my_feature" :recurs "david"}  )))
)

;######################
; MAPS with functions

(defn add-perro [aVal] (str aVal "-perro"))
(defn add-cat [aVal] (str aVal "-cat"))

(defn add_dogs_cats [values]
   (map #(% values) [add-perro add-cat])
 )

(deftest check-add-funtions
  (testing "add_perro" (is (= (add-perro "juan")  "juan-perro") ))
  (testing "add_cat" (is (= (add-cat "juan")  "juan-cat") ))
  (testing "add_dogs_cat" (is (=  (count (add_dogs_cats ["pedro" "juan"])) 2)))
)
;######################
; REDUCE
(defn add_key_val_amp [map key val]
                 (assoc map key val))
(deftest simple_actions
  (testing "t1" (is (= ((add_key_val_amp {:a1 1} :a1 2) :a1) 2 )))
  (testing "t2" (is (= ((add_key_val_amp {:a1 1} :b1 2) :b1) 2 )))
  (testing "t1" (is (= (take 2 [ 1 2 3 4 5] ) '(1 2) )))
  (testing "t1" (is (= (drop 2 [ 1 2 3 4 5] ) '(3 4 5) )))
  (testing "t1" (is (= (take-while #(< % 4) [ 1 2 3 4 5]) '(1 2 3) )))
  (testing "t1" (is (= (drop-while #(< % 5) [ 1 2 3 4 5]) '(5) )))
  (testing "t1" (is (= ( max 1 3 5) 5 )))
  (testing "t1" (is (= (apply max [1 3 5] ) 5 )))
  (def suma (partial + 5 ))
  (testing "t1" (is (= ( suma 10 ) 15 )))
)
; partial es una forma de indireccionar acciones para devolver algo que sera ejecutado luego...
; partial siempre se asigna a una variable .. no a una funcion
(defn switch_example [flag]
  (condp = flag
          :red "RED"
          :green "green"
  )
)
(deftest switch_example_test
  (testing "t1" (is (= (switch_example :red) "RED")))
  (testing "t1" (is (= (switch_example :green) "green")))
)

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


(defn my-complement
  [fun]
  (fn [& args]
    (not (apply fun args))))
(def not_greater_than_10? (my-complement #(> % 10)))
(deftest test_complement
  (testing "t1" (is (= (not_greater_than_10? 1)  true )))
  (testing "t1" (is (= (not_greater_than_10? 11)  false )))
)