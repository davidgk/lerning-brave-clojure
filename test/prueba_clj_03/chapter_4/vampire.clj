(ns prueba-clj-03.chapter-4.vampire
  (:require [clojure.test :refer :all]))
(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}})

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire?
                 (map vampire-related-details social-security-numbers))))


(defn -main []
  ;(time (println (str (vampire-related-details 0))))
  ; => "Elapsed time: 1001.042 msecs"
  ; => {:name "McFishwich", :makes-blood-puns? false, :has-pulse? true}
  (time (println (str (def mapped-details (map vampire-related-details (range 0 1000000))))))
  (time (println (str (first mapped-details ) ) ) )
  (time (println (str (first mapped-details ) ) ) )
  )
