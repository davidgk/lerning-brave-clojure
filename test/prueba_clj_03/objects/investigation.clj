(ns prueba-clj-03.objects.investigation
  (:require [clojure.test :refer :all]))


(def person {:props  {:name "David"
                      :age 48}
             :actions {:tell-your-name #(str "My name is " (-> % :props :name))
                       :add-10-to-age #(+ (-> %1 :props :age) %2)}})

(defn constructor [name age]
  (let [updater (fn [persona att val] (update-in persona [:props (keyword att)] (fn [_] val)))
        name-updated (updater person :name name)]
    (updater name-updated :age age)))

(defn exec
  ([a-person action]
   ((-> a-person :actions action) a-person))
  ([a-person action parm]
   ((-> a-person :actions action) a-person parm)))

(deftest person-test
  (let [charly (constructor "carlos" 73)]
    (is (= (-> charly :props :name) "carlos"))
    (is (= (-> charly :props :age) 73))
    (is (= ((-> charly :actions :tell-your-name) charly) "My name is carlos"))
    (is (= (exec charly :tell-your-name) "My name is carlos"))
    (is (= (exec charly :add-10-to-age 10) 83))))
