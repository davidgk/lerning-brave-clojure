(ns prueba-clj-03.chapter-10.concurrency_issues
  (:require [clojure.test :refer :all]))
;(load "../../test/prueba_clj_03/chapter-10.concurrency_issues)
;(use prueba-clj-03.chapter-10.concurrency_issues)

; DELAY: Es algo que dejo colgado y yo elijo cuando arrancarlo al ser "algo" y no una funcion.. va a dentro de un def
; FUTURE: Algo que corre y no le presto atencion pero corre... si lo necesito tengo que @/deref
; Promise: Es asignar un espacio de memoria a algo que no necesariamente esta definido y que cuando necesito lo asigo lo libero


;;; --------------------
;;; Promises

(def personas ["carlos" "victor" "samuel"])

(defn namePerson [aPerson]
  (do
    ( Thread/sleep 500)
    (str "mi nombre es " aPerson)
  )
)

(defn addPersonMap [ori , val]
  (assoc ori
    (keyword val)
    (namePerson val)
  )
)

(defn createAMap [aNameList]
  (reduce  addPersonMap {} aNameList)
)
(deftest createAMap_test
  (testing "t1" (is (= (time (createAMap  personas))  {:carlos "mi nombre es carlos"
                                               :samuel "mi nombre es samuel"
                                               :victor "mi nombre es victor"} )))
)

