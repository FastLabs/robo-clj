(ns robo-clj.touch
  (:require [clj-ev3dev.sensors.touch :as touch]
            [clj-ev3dev.ssh :as ssh]
            [clj-ev3dev.devices :as devices]))

(def session (ssh/create-session {:ip-address               "192.168.2.2"
                                  :username                 "robot"
                                  :password                 "maker"
                                  :strict-host-key-checking :no}))

(def config {:env :remote :session session})

(prn "Loading sensor")

(def touch-sensor (devices/find-sensor config {:device-type :touch :port :one}))

(prn (str "sensor value: " (touch/pressed? config touch-sensor)))




