from py4j.java_gateway import JavaGateway
gateway = JavaGateway()

graal = gateway.entry_point
print(graal)


res = graal.evaluate("?(A,B,C,D,M,E,F,G,H,K) :- " + " passagerRelation(A,B,C,D)," + " cabineRelation(A,B,M)," + " aPourClasse(A,B,E)," + " voyageTitanic(A,B,F,G,H,I,J,K,L).")

while res.hasNext():
    print(res.next())