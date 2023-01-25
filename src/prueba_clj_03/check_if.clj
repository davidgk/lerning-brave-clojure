(ns prueba-clj-03.check_if
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
