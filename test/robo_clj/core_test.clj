(ns robo-clj.core-test
  (:require [clojure.test :refer :all]
            [clojure.core.async :as ay]
            [robo-clj.core :refer :all]))

; do i really need a suspendable chanel?
(defprotocol SuspendableChanel
  (suspend! [this])
  (resume [this]))


(defn suspend-if-required
  [status suspend-chan]
  (let [max-size 1000
        {:keys [buff-size suspended?]} @status]
    (when (and (not suspended?)
               (> buff-size max-size))
      (ay/put! suspend-chan :suspend)
      (swap! status assoc :suspended? true))))

(defn resume-if-required
  [res-chan status resume-chan]
  (ay/go
    (let [{:keys [update-count]} (ay/<! res-chan)])))

(defn persist [db val]
  val)

(defn suspend-chan
  [in-chan suspend-ch resume-ch]
  (let [max-size 1000
        min-size 100
        db {}
        in-chan (ay/chan)
        status (atom {:suspended? false
                      :completed? false
                      :buff-size  0})]
    (ay/go-loop []
      (when-let [to-persist (ay/<! in-chan)]
        (suspend-if-required status suspend-ch)
        (-> (persist db to-persist)
            (resume-if-required status resume-ch))
        (recur)))))

(deftest a-test
  (testing "Suspend resume interface"
    (let [ch (suspend-chan (ay/chan) (ay/chan))]
      (is (nil? (resume ch))))))
