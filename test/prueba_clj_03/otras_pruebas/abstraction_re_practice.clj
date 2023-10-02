(ns prueba-clj-03.otras-pruebas.abstraction-re-practice
  (:require [clojure.test :refer :all]))
;;============================================================================================================
;; El valor del dispatcher es el que es elegido para disparar la funcion , es como un state pattern
;; Puede ser una estrategia  ay que podemos traducir mediante condiciones especificas el tipo de valor que disparemos y
;; esto produce una funcion especifica.
;; Este es el delivery el selector de la funcion
(def dispatcher-mechanic (fn [worker] (:type worker)))
(defmulti mechanic  (fn [worker] (:type worker)))
(defmethod mechanic :wheels
  [worker]
  (str (:name worker) " will fix your wheels"))

(defmethod mechanic :engine
  [worker]
  (str (:name worker) " will fix your engine"))
(defmethod mechanic :default
  [worker]
  (str (:name worker) " will fix general issues"))

(defn obtain-params [param1]
  (case param1
    (not (contains? [:obtain-wheels :obtain-engine] param1 )) :default
    :obtain-wheels  :wheels
    :obtain-engine  :engine))

(deftest mechanic-test
  (testing "test 1"
    (let [param (obtain-params :obtain-wheels)
          wheels (mechanic {:type param :name "Juan" })]
      (is (= wheels "Juan will fix your wheels"))))
  (testing "test 2"
    (let [wheels (mechanic {:type :engine :name "Pedro" })]
      (is (= wheels "Pedro will fix your engine"))))
  (testing "test 3"
    (let [wheels (mechanic {:type nil :name "Pedro" })]
      (is (= wheels "Pedro will fix general issues")))))

;;============================================================================================================
(defrecord Persona [rol nombre])
(def juan (Persona. :admin "Juan"))
(def pedro (Persona. :dev "Pedro"))
(def carlos (Persona. :tester "Carlos"))

(defmulti check-rol (fn [persona tool] (:rol persona)))
(defmethod check-rol :admin
  [persona params]
  (str (:nombre persona) " es admin y usa para admin " (:tool params)))
(defmethod check-rol :dev
  [persona params]
  (str (:nombre persona) " es dev y usa " (:tool params)))
(deftest check-rol-test
  (testing "test 1"
    (let [rol (check-rol juan {:tool "java" })]
      (is (= rol "Juan es admin y usa para admin java"))))
  (testing "test 2"
    (let [rol (check-rol pedro {:tool "java" })]
      (is (= rol "Pedro es dev y usa java")))))

;;============================================================================================================
(defprotocol Worker
  (work [worker] "work"))
(defprotocol Doctor
  (heal [doctor] "heal"))

(defrecord Obrero [name])
(defrecord Obranza [name])
(defrecord Dentista [name])
(defrecord Cirujano [name])

(extend-protocol Worker
  Obrero
  (work [worker]
    (str (:name worker) " is obrero"))
  Obranza
  (work [worker]
    (str (:name worker) " is obranza")))

(extend-protocol Doctor
  Dentista
  (heal [doctor]
    (str (:name doctor) " is dentista"))
  Cirujano
  (heal [doctor]
    (str (:name doctor) " is cirujano")))

(deftest WorkerTest
  (testing "test 1"
    (let [worker (work (Obrero. "Juan"))]
      (is (= worker "Juan is obrero"))))
  (testing "test 2"
    (let [worker (work (Obranza. "Pedro"))]
      (is (= worker "Pedro is obranza"))))

  (testing "test 3"
    (let [worker (heal (Dentista. "Carlos"))]
      (is (= worker "Carlos is dentista"))))
  (testing "test 4"
    (let [worker (heal (Cirujano. "Carlos"))]
      (is (= worker "Carlos is cirujano")))))

;; En conclusion un protocolo define una interfaz y un multimethod define una funcion que se va a ejecutar en funcion de un valor
;; esta interfaz es aplicable a un tipo de dato y el multimethod es aplicable a un valor.
;; la implementacion de la interfaz se hace medinate un extend-type o extend-protocol
;; con extend-protocol puedo dar una implementacion por defecto a un protocolo, varias imlpementaciones iguales o distintas
;; a otros objetos para el mismo protocolo
;; Cuando se extiende el protocolo estoy diciendo que para un mismo protocolo puedo implementar en varios tipos el mismo protocolo
;; Cuando extiende el tipo puedo para el mismo objeto implememtar varios protocolos.. pero tener en cuenta que el filtro del
;; objeto esta ddao por las funcionalides que posee. osea los distintos protocolos que implementa.
;;
;;
;; extend protocol Protocol y objetos que implemetnan el metodo del protocolo
;; extend type es el Objecto y los protocolos a implmentar.

;;============================================================================================================

(defrecord Engineer [name])

(defprotocol Builder
  (build [build] "build"))
(extend-type Engineer
  Worker
  (work [engineer]
    (str (:name engineer) " is engineer"))
  Builder
  (build [engineer]
    (str (:name engineer) " is builder")))

(deftest Engineer-test
  (testing "test 1"
    (let [engineer (Engineer. "Juan")
          worker (work engineer)
          builder (build engineer)]
      (is (= worker "Juan is engineer"))
      (is (= builder "Juan is builder")))))


;;============================================================================================================
;
;(deftype Professor [name])
;
;(defprotocol Teacher
;  (teach-math [teacher] "teach something"))
;(extend-type Professor
;  Teacher
;  (teach-math [teacher]
;    (str (:name teacher) " teach mathematics")))
;
;(deftest Professor-test
;  (testing "test 1"
;    (let [professor (Professor. "Juan")
;          teacher (teach-math professor)]
;      (is (= teacher "Juan teach mathematics")))))