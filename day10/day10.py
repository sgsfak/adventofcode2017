

def select_sublist_indices(seq, pos, length):
    N = len(seq)
    indices = [ (pos+i) % N  for i in range(length)]
    # print indices
    return indices

def rev_sublist_indices(seq, indices):
    sublist = [seq[i] for i in reversed(indices)]
    for i, e in zip(indices, sublist):
        seq[i] = e
    return seq

def print_list(seq, pos):
    s = ""
    for i, e in zip(range(len(seq)), seq):
        if i==pos:
            s += "[%d]" % e
        else:
            s += "%d" % e
        s += " "
    print s

def doit(initial_seq, input_lengths, pos, skip):
    seq = initial_seq
    N = len(initial_seq)
    for length in input_lengths:
        inds = select_sublist_indices(seq, pos, length)
        seq = rev_sublist_indices(seq, inds)
        pos = (length+skip+pos) % N
        skip += 1
    return (seq, pos, skip)


def part1():
    initial_seq = range(256)
    input_lengths = [106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36]
    pos = 0
    skip = 0
    seq, _, _ = doit(initial_seq, input_lengths, pos, skip)
    return seq[0]*seq[1]

def compute_xor(lst):
    "compute_xor([65 , 27 , 9 , 1 , 4 , 3 , 40 , 50 , 91 , 7 , 6 , 0 , 2 , 5 , 68 , 22]) == 64"
    a = lst[0]
    for i in range(1, len(lst)):
        a ^= lst[i]
    return a

def lst_to_hex(lst):
    s = ""
    for i in lst:
        s += "%0.2x" % i
    return s

input = "106,16,254,226,55,2,1,166,177,247,93,0,255,228,60,36"
def part2(input):
    input_lengths = [ord(c) for c in input]
    input_lengths.extend([17, 31, 73, 47, 23])
    initial_seq = range(256)
    pos = 0
    skip = 0
    seq = initial_seq
    for i in range(64):
        seq, pos, skip = doit(seq, input_lengths, pos, skip)
    sparse_hash = seq
    dense_hash = []
    for i in range(16):
        dense_hash.append(compute_xor([sparse_hash[16*i+j] for j in range(16)]))
    return lst_to_hex(dense_hash)









