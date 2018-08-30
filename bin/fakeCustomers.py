from faker import Faker
fake = Faker()

mil=['APO','FPO','DPO']

f = open("../data/customer.orig.txt")
o = open("../data/customer.txt", "w+")
for l in f:
    ll = l.split('\t')
    c_id = ll[0]
    c_d_id = ll[1]
    c_w_id = ll[2]
    n = fake.name()
    c_first = n.split()[0]     
    c_last = n.split()[1]     
    a = fake.address()
    keepGoing = True
    while keepGoing == True:
        t = a.split('\n')
        s = True
        if "APO" in t[1]:
            s = False
        if "DPO" in t[1]:
            s = False
        if "FPO" in t[1]:
            s = False
        if s == True:
            keepGoing = False
        else:
            a = fake.address()
        
         
    t = a.split('\n')
    c_street = t[0]
    c_city = t[1].split(',')[0]
    c_state = t[1].split(',')[1].split()[0]
    c_zip = t[1].split(',')[1].split()[1]

    os = "{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\t{}\n".format(c_id, \
        c_d_id, c_w_id, c_first, c_last, c_street, c_city, c_state, c_zip)
    o.write(os)

o.close()
