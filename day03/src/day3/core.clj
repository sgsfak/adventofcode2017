(ns day3.core)

(defn find-square 
   "Find the 0-based number of square that contains the given number.
   In general the K-th square contains the numbers from (2*K-1)^2 + 1 until (2*K+1)^2

   The K-th square (for K > 0) has 2*K+1 in each side for a total of
    2(2*K+1 +2*K+1 - 2)= 8*K numbers. 
    The 4 corners are the numbers: (2*K+1)^2 , (2*K+1)^2 - 2*K, (2*K+1)^2 - 4*K, (2*K+1)^2 - 6*K"
	[n]
	(let [K (-> n 
				dec 
				Math/sqrt 
				inc 
				(/ 2)
				Math/floor
				int)]
	 K))

(defn sqr [x] (* x x))

(defn solution-part1 [n]
	(let [K (find-square n)
		  a (-> K (* 2) inc)
		  d (- n (-> K (* 2) dec sqr))
		  i (mod d (* 2 K))
		  dd (- i K)]
		(+ K (Math/abs dd))))

(defn is-corner? 
	"Checks if the n-th element (starting from 1) is a corner in the k-th square
	"
	[k, n]
	(let [c4 (-> k (* 2) inc sqr)
		  c3 (- c4 (* 2 k))
		  c2 (- c4 (* 4 k))
		  c1 (- c4 (* 6 k))]
		(or (= n c1) (= n c2) (= n c3) (= n c4))))

(defn step1-aux 
    "The input vector corresponds to the values of the k-th square.
    In this step we "
	[k vk vnext n lst]

	(let [ind-x (if (is-corner? k n) [-1 0 1 2 3] [-1 0 1])
		  ind (mapv #(+ (mod (dec (+ % lst)) (inc k)) 1) ind-x)
		  new-lst (last ind)
		  v (vk n)
          vnxt (reduce #(do
          	               (println [%2])
          	               (update-in %1 [%2] + v)) vnext ind)]
     [vnxt (inc n) new-lst]))

(defn step1 [k vk]
 (let [l (-> k inc (* 8) inc)
 	   sz (* 8 (+ k 1))
 	   vknext (mapv identity (repeat l 0))]
 	(reduce (fn [[vnxt n new-lst] i] 
 		(step1-aux k vk vnxt n new-lst)) [vknext 1 2] (range sz))))


