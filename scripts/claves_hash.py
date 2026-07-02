def hash_manual(clave, tamano):
    suma = sum(ord(c) for c in clave)
    return suma, suma % tamano

names = ["Jack Williams", "Sam Taylor", "Sandra Miller", "Mattew Davis", "Andrew Wilson"]

print("ASCII sums:")
for name in names:
    suma, _ = hash_manual(name, 11)
    print(f"{name}: {suma}")

print("\nProbar diferentes tamaños de tabla para encontrar "
        +"un patrón de colisión coincidente:")
for size in range(5, 20):
    buckets = {}
    for name in names:
        _, idx = hash_manual(name, size)
        buckets[name] = idx
    if buckets["Jack Williams"] == buckets["Andrew Wilson"]:
        if buckets["Sam Taylor"] < buckets["Jack Williams"] < buckets["Sandra Miller"] < buckets["Mattew Davis"]:
            print(f"El tamaño {size} coincide con las condiciones"
                +" del pedido. Índices: {buckets}")