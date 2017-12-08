from collections import defaultdict

with open("day8.txt") as f:
    lines = [s.strip().split(" ") for s in f.readlines()]


def comparator(registers, line):
    op = line[5]
    if op == '<=' : return (lambda x, v: registers[x] <= v)
    if op == '>=' : return (lambda x, v: registers[x] >= v)
    if op == '<'  : return (lambda x, v: registers[x] < v)
    if op == '>' : return (lambda x,v : registers[x] > v)
    if op == '==' : return (lambda x,v: registers[x] == v)
    if op == '!=' : return (lambda x,v: registers[x] != v)
    return None


def if_apply(registers, line):
    "['kd', 'dec', '-37', 'if', 'gm', '<=', '9']"
    comp = comparator(registers, line)
    op = line[1]
    reg = line[0]
    if comp(line[4], int(line[-1])):
        val = int(line[2])
        if op == 'dec':
            registers[reg] -= val
        else:
            registers[reg] += val

def part1(lines):
    registers = defaultdict(int)
    for line in lines:
        if_apply(registers, line)
    return max(registers.values())



def part2(lines):
    registers = defaultdict(int)
    mm = -1
    for line in lines:
        if_apply(registers, line)
        m = max(registers.values())
        if m > mm: mm = m
    return mm

