(ns day5.core)

(def input (->> "resources/input"
                slurp
                clojure.string/split-lines
                (mapv #(Integer/parseInt %)))) 


(defn jump [input pos]
   ;;(println "Now at pos = " pos " for input = " input)
   (let [len (count input)
         new-pos (+ (input pos) pos)
         valid-offset? (and (>= new-pos 0) (< new-pos len))] 
      (if valid-offset? 
          [(update-in input [pos] inc) new-pos]
          nil)))


(defn solution1 [input]
  (loop [i 1 pos 0 input input]
    (if-let [[new-state new-pos] (jump input pos)]
       (recur (inc i) new-pos new-state)
       i)))
     

(defn jump2 [input pos]
   ;;(println "Now at pos = " pos " for input = " input)
   (let [len (count input)
         offset (input pos)
         new-pos (+ offset pos)
         valid-offset? (and (>= new-pos 0) (< new-pos len))
         change (if (>= offset 3) dec inc)] 
      (if valid-offset? 
          [(update-in input [pos] change) new-pos]
          nil)))


(defn solution2 [input]
  (loop [i 1 pos 0 input input]
    (if-let [[new-state new-pos] (jump2 input pos)]
       (recur (inc i) new-pos new-state)
       i)))
