(ns prueba-clj-03.chapter_3.data_structures
  (:gen-class))

(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))


(def my_map  {:carlos "carlos en tanga" :julian "cabezadura" :cabeza "amigo de siempre"})
