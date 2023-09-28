(ns prueba-clj-03.otras-pruebas.ejemplos_protocolos_test
  (:require [clojure.test :refer :all]))

(defrecord Car [gears-vs-speeds])

(defrecord Driver [car])

(defn change-gear [driver position-gear]
  (-> driver :car :gears-vs-speeds position-gear))

(deftest driver-gear-test
  (testing "Cuando un conductor pone primera el automovil se debe mover a 30"
    (let [
          a-car (Car. {:first-gear 30 :second-gear 60 :third-gear 90})
          a-driver (Driver. a-car)
          ]
      (is (= (change-gear a-driver :first-gear) 30))
      (is (= (change-gear a-driver :second-gear) 60))
      (is (= (change-gear a-driver :third-gear) 90)))))
