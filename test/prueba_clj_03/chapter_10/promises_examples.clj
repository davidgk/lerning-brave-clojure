(ns prueba-clj-03.chapter-10.promises-examples
  (:require [clojure.test :refer :all]))


; Promise: Es asignar un espacio de memoria a algo que no necesariamente esta definido y que cuando necesito lo asigo lo libero

(defn miPromesa [ab]
  (def my-promise (promise))
  (deliver my-promise (+ 1 ab))
  @my-promise
  )

(deftest promesas
  (testing "t1" (is (= (miPromesa 4) 5)))
  )

(defn sumar3 [b] (+ 3 b))
(defn sumar5 [b] (+ 5 b))

(defn awp [x]
  (def myPromise (promise))
  (if (> x 10)
    ( do
      (def a (deliver myPromise (sumar3 x) ))
      a                                                     ; devuelvo una promesa
    )
    ( do
      (def b (deliver myPromise (sumar5 x) ))
      b                                                     ; devuelvo una promesa
      )
  )
)

(deftest actionWithPromise_test
  (is (= @(awp 5)  10))
  (is (= @(awp 20) 23))
)

