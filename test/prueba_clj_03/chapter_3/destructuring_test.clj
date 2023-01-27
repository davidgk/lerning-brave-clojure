(ns prueba-clj-03.chapter_3.destructuring-test
  (:require [clojure.test :refer :all]))

(def family '("lourdes" "gaby" "cata"))

(defn mandados [[spouse son daugther]]
  (def lista [(str spouse " comprar milanesas")
              (str son " anda al almacen")
              (str daugther " compra helados")
              ])
  lista
  )
(deftest mandados-test
  (is (= (mandados family) ["lourdes comprar milanesas"
                            "gaby anda al almacen"
                            "cata compra helados"]))
  )
(def  task01 {:p1 "corrimos" :p2 "caminamos"})
(defn ex1 [{correr :p1 caminar :p2}]
  (def accion (str "hoy " correr))
  accion
  )
(defn ex2
  [{:keys [correr caminar]}]
  (def accion (str "hoy " correr " y " caminar))
  accion
  )


(deftest ex-test
  (testing "t1" (is (= (ex1 task01) "hoy corrimos")))
)

(def ej1 (let [[lourdes & chicos] family] [lourdes chicos]))
(deftest ej1-test
  (testing "t1" (is (= (ej1 0) "lourdes")))
  (testing "t2" (is (= (ej1 1) ["gaby" "cata"])))
)