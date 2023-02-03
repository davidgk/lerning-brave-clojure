(ns prueba-clj-03.chapter_3.check_if
  (:gen-class))


(defn def_if [x]
  (if (>= x 2)
    :holly_shirt
  )
)


(defn first_if [x]
  (if (>= x 2)
    (str "over 2")
    (str "less 2")
    )
  )

(defn scnd_if [x]
  (if (>= x 2)
    "over 2"
    "less 2"
  )
)

(defn third_if [x]
  (if (and (>= x 2) (< x 20) )
    :valid
    (if (or (> x 25) (< x 100))
      :super_valid
      :invalid)
  )
)

;(load "chapter_3/check_if")
;(in-ns 'prueba-clj-03.chapter_3.check_if)