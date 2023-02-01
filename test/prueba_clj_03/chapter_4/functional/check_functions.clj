(ns prueba-clj-03.chapter-4.functional.check-functions
  (:require [clojure.test :refer :all]))

(def values [1 2 3])

(defn adding [a b] (+ a b))


(defn sum-values-recur
  ([vals] (sum-values-recur vals 0))
  ([vals accumulating-total]
     (if (empty? vals)
       accumulating-total
       (sum-values-recur (rest vals) (+ (first vals) accumulating-total)))))
(defn sum-values-for1 [params]   (def x 0)   (for [i params]    (print (+ i x))))
(defn sum-values-for [params]
  (reduce (fn [x i] (+ x i)) 0 params)
)
(deftest test-sum
  ;(testing "sum recur" (is (= (sum-values-recur values) 6)))
  (testing "sum for" (is (= (sum-values-for values) 6)))
)

(defn -main [args] (sum-values-for values))