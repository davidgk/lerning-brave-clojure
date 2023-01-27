(ns prueba-clj-03.chapter_3.do_when_examples
  (:gen-class))


(defn def_do [msg]
  (do
    (def pepe (str msg " ahi vamos"))
    (str pepe)
  )
)


(defn def_when [msg name]
  (def var (when (= msg "hola")
             (do
               (def greet (str msg " mundo"))
               (def enhance_greet (str greet ", " name))

               (str enhance_greet)
               )
             )
  )
  (if (nil? var)
    "No saludamos"
    (str var)
  )
)
