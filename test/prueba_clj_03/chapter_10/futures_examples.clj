(ns prueba-clj-03.chapter-10.futures-examples
  (:require [clojure.test :refer :all]))


(defn f1 [val, optionDeref]
  (if optionDeref
    (deref (future (println "vamos") (+ 1 val )))
    @(future (println "vamos with @") (+ 1 val ))
    )
  )
(deftest f1_02
  (testing "deref" (is (= (f1 5 true) 6 )))
  (testing "@ test" (is (= (f1 5 false) 6 )))
  )

; FUTURE: Algo que corre y no le presto atencion pero corre... si lo necesito tengo que @/deref

(defn largandoFuturos [] ( do
                           (def a1 @tareaDelay)
                           (future ( (Thread/sleep 2000) (println "action 1"))  (a1 1 3))
                           (future ( (Thread/sleep 4000) (println "action 2")) (a1 1 4))
                           (future ( (Thread/sleep 500) (println "action 3")) (a1 1 7))
                           ))


(deftest fut01_future_and_deref
  (testing "t1" (is (= (deref (future (Thread/sleep 1000) 0) 10 5) 5 )))
  (testing "t2" (is (= (deref (future (Thread/sleep 1000) 0) 2000 10) 0 )))
  (testing "t3" (is (= (f1 5 true) 6 )))
  )
