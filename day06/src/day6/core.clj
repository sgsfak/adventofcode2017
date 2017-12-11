(ns day6.core)

(def input (as-> "resources/input" $
                (slurp $)
                (clojure.string/split $ #"\s") 
                (mapv #(Integer/parseInt %) $)))

(defn select-bank [banks]
  (let [ [j _]
        (reduce (fn [[current-min-index current-min] i] 
                  (if (> (banks i) current-min) [i (banks i)]
                    [current-min-index current-min]))

                [0 -1] (range (count banks)))]
    j))

(defn distribute [input]
   ;;(println "Now at pos = " pos " for input = " input)
   (let [len (count input)
         bank (select-bank input)
         blocks (input bank)
         cycles (quot blocks len)
         rest-blocks (mod blocks len)
         bb (mapv #(+ cycles %) input)
         rest-indices (mapv #(mod (+ % bank 1) len) (range rest-blocks ))
         bbb (reduce  #(update-in %1 [%2] inc) bb rest-indices)
         ]
      (update-in bbb [bank] #(- % blocks))))


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
