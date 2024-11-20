import pandas as pd
import matplotlib.pyplot as plt

# ---------------- MERGESORT 1 ----------------
data_mergesort1 = pd.DataFrame({
    "Array Type": [
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"
    ],
    "Size": [
        10, 10, 10, 10,
        100, 100, 100, 100,
        1000, 1000, 1000, 1000,
        10000, 10000, 10000, 10000,
        100000, 100000, 100000, 100000,
        1000000, 1000000, 1000000, 1000000
    ],
    "Average Memory Used (bytes)": [
        92830, 31072, 30960, 30960,
        125840, 125840, 125840, 125840,
        1124960, 1124960, 1124960, 1124960,
        9665964, 9639193, 9624366, 9641159,
        21135438, 22129817, 21292332, 21292891,
        173734449, 405856197, 377872784, 396681742
    ]
})

plt.figure(figsize=(14, 8))
for array_type in data_mergesort1['Array Type'].unique():
    subset = data_mergesort1[data_mergesort1['Array Type'] == array_type]
    plt.plot(subset['Size'], subset['Average Memory Used (bytes)'], marker='o', label=array_type)

plt.ticklabel_format(style='plain', axis='y')
plt.ticklabel_format(style='plain', axis='x')
plt.title('MergeSort 1: Memory Usage vs. Array Size (All Input Types)', fontsize=16)
plt.xlabel('Array Size (elements)', fontsize=14)
plt.ylabel('Memory Usage (bytes)', fontsize=14)
plt.legend(fontsize=12)
plt.grid()
plt.savefig("mergesort1_memory_vs_size.png")
plt.show()

# ---------------- MERGESORT 2 ----------------
data_mergesort2 = pd.DataFrame({
    "Array Type": [
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"
    ],
    "Size": [
        10, 10, 10, 10,
        100, 100, 100, 100,
        1000, 1000, 1000, 1000,
        10000, 10000, 10000, 10000,
        100000, 100000, 100000, 100000,
        1000000, 1000000, 1000000, 1000000
    ],
    "Average Memory Used (bytes)": [
        9290, 3114, 3104, 3104,
        7088, 7088, 7088, 7088,
        93560, 93560, 93560, 93560,
        810575, 808149, 807526, 807526,
        832831, 813947, 813973, 813937,
        13746714, 43350912, 43713627, 43711162
    ]
})

plt.figure(figsize=(14, 8))
for array_type in data_mergesort2['Array Type'].unique():
    subset = data_mergesort2[data_mergesort2['Array Type'] == array_type]
    plt.plot(subset['Size'], subset['Average Memory Used (bytes)'], marker='o', label=array_type)

plt.ticklabel_format(style='plain', axis='y')
plt.ticklabel_format(style='plain', axis='x')
plt.title('MergeSort 2: Memory Usage vs. Array Size (All Input Types)', fontsize=16)
plt.xlabel('Array Size (elements)', fontsize=14)
plt.ylabel('Memory Usage (bytes)', fontsize=14)
plt.legend(fontsize=12)
plt.grid()
plt.savefig("mergesort2_memory_vs_size.png")
plt.show()

# ---------------- MERGESORT 3 ----------------
data_mergesort3 = pd.DataFrame({
    "Array Type": [
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array",
        "Random Array", "Sorted Array", "Reversed Array", "Nearly Sorted Array"
    ],
    "Size": [
        10, 10, 10, 10,
        100, 100, 100, 100,
        1000, 1000, 1000, 1000,
        10000, 10000, 10000, 10000,
        100000, 100000, 100000, 100000,
        1000000, 1000000, 1000000, 1000000
    ],
    "Average Memory Used (bytes)": [
        9314, 3366, 3128, 3128,
        3488, 3488, 3488, 3488,
        7088, 7088, 7088, 7088,
        43088, 43088, 43088, 43088,
        423550, 424095, 424095, 424095,
        4194304, 4194304, 4194304, 4194304
    ]
})

plt.figure(figsize=(14, 8))
for array_type in data_mergesort3['Array Type'].unique():
    subset = data_mergesort3[data_mergesort3['Array Type'] == array_type]
    plt.plot(subset['Size'], subset['Average Memory Used (bytes)'], marker='o', label=array_type)

plt.ticklabel_format(style='plain', axis='y')
plt.ticklabel_format(style='plain', axis='x')
plt.title('MergeSort 3: Memory Usage vs. Array Size (All Input Types)', fontsize=16)
plt.xlabel('Array Size (elements)', fontsize=14)
plt.ylabel('Memory Usage (bytes)', fontsize=14)
plt.legend(fontsize=12)
plt.grid()
plt.savefig("mergesort3_memory_vs_size.png")
plt.show()

---------------- MERGESORT 4 ----------------
data_mergesort4 = pd.DataFrame({
    "Array Type": [
        "Random", "Sorted", "Reversed", "Nearly Sorted",
        "Random", "Sorted", "Reversed", "Nearly Sorted",
        "Random", "Sorted", "Reversed", "Nearly Sorted",
        "Random", "Sorted", "Reversed", "Nearly Sorted",
        "Random", "Sorted", "Reversed", "Nearly Sorted",
        "Random", "Sorted", "Reversed", "Nearly Sorted"
    ],
    "Size": [
        10, 10, 10, 10,
        100, 100, 100, 100,
        1000, 1000, 1000, 1000,
        10000, 10000, 10000, 10000,
        100000, 100000, 100000, 100000,
        1000000, 1000000, 1000000, 1000000
    ],
    "Average Memory Used (bytes)": [
        9283, 3107, 3096, 3096,
        6192, 6192, 6192, 6192,
        37152, 37152, 37152, 37152,
        272233, 270942, 270942, 270944,
        2478139, 2515608, 2515600, 2515600,
        24035978, 24119296, 24119296, 24119296
    ]
})

# Plotting the graph for MergeSort 4
plt.figure(figsize=(14, 8))
for array_type in data_mergesort4['Array Type'].unique():
    subset = data_mergesort4[data_mergesort4['Array Type'] == array_type]
    plt.plot(subset['Size'], subset['Average Memory Used (bytes)'], marker='o', label=array_type)

plt.ticklabel_format(style='plain', axis='y')
plt.ticklabel_format(style='plain', axis='x')
plt.title('MergeSort 4: Memory Usage vs. Array Size (All Input Types)', fontsize=16)
plt.xlabel('Array Size (elements)', fontsize=14)
plt.ylabel('Memory Usage (bytes)', fontsize=14)
plt.legend(fontsize=12)
plt.grid()
plt.savefig("mergesort4_memory_vs_size.png")
plt.show()