(ns prueba-clj-03.otras-pruebas.loop-recur
  (:require
    [clojure.core.async :as async]
    [clojure.test :refer :all]))


; If I receive a list of [0 1 2], I want to add 5 to each stuff I should retunr [5 6 7]
(defn iterar-lista-1 [lista]
  (let [origin (ref [])]
    (loop [xs lista]
      (if (empty? xs)
        nil
        (do
          (println @origin)
          (dosync (ref-set origin (conj @origin (+ 5 (first xs)))))
          (recur (rest xs)))))
  @origin
  ))

(deftest iterar-lista-test
  (let [mi-lista '(1 2 3 4 5)]
    (testing "t1"
      (is (= [6 7 8 9 10] (iterar-lista-1 mi-lista)))) ))


