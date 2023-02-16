(ns prueba-clj-03.chapter-10.delays-examples
  (:require [clojure.test :refer :all]))


; DELAY: Es algo que dejo colgado y yo elijo cuando arrancarlo al ser "algo" y no una funcion.. va a dentro de un def

(def tareaDelay (delay (fn [a b] (+ a b))))
(defn executeDelay [option]
  (if option
    tareaDelay
    8
    )
  )

(deftest executeDelaytest
  (def myAction (force (executeDelay true)))
  (is (= (myAction 5 5)   10))
  (is (= (myAction 1 6)   7))
  (def myAction2 @(executeDelay true))
  (is (= (myAction2 5 5)   10))
  (is (= (myAction2 1 6)   7))
  (is (= (@(executeDelay true) 1 6)   7))
  (is (= (executeDelay false) 8))
  )


(def del01 (delay (let [m1 "mensaje"] (def m2 (str m1 " enviado")) m2)))

(deftest delay_test
  (is (= (:status del01 ) nil))
  (is (= @del01 "mensaje enviado"))
  )