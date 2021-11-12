#!/usr/bin/env python
# coding: utf-8

# __Cuaderno de trabajo de:__ Nombre Apellido

# ## Lectura y escritura de archivos
# 
#     f = open("archivo.txt", "w")
#     f.write('Hola mundo\n')
# tenemos los siguientes modos:
# - ‘r’ : Abre el archivo en modo lectura.
# -‘w’ : write, escritura. Abre el archivo en modo escritura.
# -‘a’ : append, se diferencia del modo ‘w’ en que en este caso no se sobreescribe el contenido del archivo, sino que se comienza a escribir al final del archivo.
# -‘b’ : binary, binario.
# -‘+’ : permite lectura y escritura simultáneas.
# -‘U’ : universal newline, saltos de línea universales

# In[1]:


f = open("archivo.txt", "r")
print(f)


# In[2]:


f = open("archivo.txt", "r")
completo = f.read()
print(completo)
f.close()


# In[3]:


f = open("archivo.txt", "r")
parte = f.read(8)
print(parte)
f.close()


# In[15]:


f = open("archivo.txt", "r")
while True:
    linea = f.readline()
    if not linea: 
        break
    print(linea)


# In[5]:


archivo="archivo.txt"
text=open(archivo).readlines()
nmaxlines=len(text)
for line in text:
    print(line.split())


# ## Manejando excepciones

# In[6]:


archivo="archivo.txt"
try:
    f = open(archivo, 'r')
except IOError:
    print('no pude abrir', archivo)
else:
    print(archivo, 'tiene', len(f.readlines()), 'lineas')
    f.close()

archivo="no_existe.txt"
try:
    f = open(archivo, 'r')
except IOError:
    print('no pude abrir', archivo)
else:
    print(archivo, 'tiene', len(f.readlines()), 'lineas')
    f.close()


# ## Format
# La forma más sencilla de mostrar algo en la salida estándar es median-
# te el uso de la sentencia print, la cual permite también utilizar técnicas avanzadas de formateo, de forma similar al sprintf de C
# Los especificadores más sencillos están formados por el símbolo % seguido de una letra que indica el tipo con el que formatear el valor Por ejemplo:
# 
# Especificador | Formato
# :--------: | :-------:
# %s |Cadena
# %d |Entero
# %o |Octal
# %x |Hexadecimal
# %f |Real
# 

# In[7]:


print ("%10s mundo" % "Hola")


# In[8]:


print ("%-10s mundo" % "Hola")


# In[24]:


from math import pi
print("%.4f" % pi)
print("%.6f" % pi)
print("%.4f" % pi,"%.6f" % pi)


# In[10]:


print ("%.4s" % "hola mundo")


# In[6]:


n=123.456789
cadena='{0:12.6f}  '.format(n)
cadena=cadena+'{0:12.6f}  '.format(n)
cadena=cadena+"\n" 
n=9876.54321
cadena=cadena+'{0:12.6f}  '.format(n)
cadena=cadena+'{0:12.5f}  '.format(n)
print(cadena)


# <hr>
# <b><font color='red'>Ejercicio 01</font></b>  
# 
# Lee el archivo.txt 

# In[23]:


f=open("archivo.txt").readlines()
for line in f:
    list = line.split()
    contador = 0
    for i in list:
        if contador == 0:
            print("%.1f" % int(i),end="  ")
            contador+=1
        else:
            print("%.2f" % int(i),end="\n")
            contador = 0
    


# y haz que tenga la siguiente salida:
# 
#     1.0   2.00  
#     2.0   4.00  
#     3.0   6.00  
#     4.0   8.00  
# 

# <hr>
# <b><font color='red'>Ejercicio 02</font></b>  
Haz lo mismo con el archvo archivo_error.txt,con cuidado de que a este archivo le falta algún dato, haz que en ese caso cuando lo lea, si no hay dos columnas no lo escriba.
# In[22]:


f=open("archivo_error.txt").readlines()
for line in f:
    list = line.split()
    contador = 0
    if len(line) != 2:
        for i in list:
            if contador == 0:
                print("%.1f" % int(i),end="  ")
                contador+=1
            elif len(i) == 0:
                contador == 0
            else:
                print("%.2f" % int(i),end="\n")
                contador = 0


# <hr>
# <b><font color='red'>Ejercicio 03</font></b>  

# Lee el archivo.txt, haz que imprima las columnas en las filas y las filas en las columnas

# In[96]:


f=open("archivo.txt").readlines()
contador = 0
for i in range(2):
    for line in f:
        list = line.split()
        if contador == 0:
            print("%.1f" % int(list[0]),end="  ")
        else:
            print("%.1f" % int(list[1]),end="  ")
    print("\n")
    contador += 1


# <hr>
# <b><font color='red'>Ejercicio 04</font></b>  

# 1. Escribe en un archivo la siguiente matriz:
# $
# \begin{equation}
# \begin{pmatrix}
# 3 & 1 & 3\\
# -1 & 1 & 0\\
# -2 & 4 & 1
# \end{pmatrix}
# \end{equation} 
# $
# 2. Lee el archivo
# 3. Calcula con los datos leidos el determinante de la matriz y lo saque por pantalla

# In[158]:


f = open("matriz.txt", "w")
f.write('3 1 3\n')
f.write('-1 1 0\n')
f.write('-2 4 1')
f.close()
f = open("matriz.txt", "r").readlines()
m = []
for line in f:
    m.append(line.split())
print("Resultado de la matriz: ", m)

i =( (int(m[0][0])*int(m[1][1])*int(m[2][2]))
    +(int(m[1][0])*int(m[2][1])*int(m[0][2]))
    +(int(m[2][0])*int(m[0][1])*int(m[1][2])) )-( (int(m[2][0])*int(m[1][1])*int(m[0][2]))
     +(int(m[0][0])*int(m[2][1])*int(m[1][2]))
     +(int(m[1][0])*int(m[0][1])*int(m[2][2])) )
    
print(i)


# <hr>
# <b><font color='red'>Ejercicio 05</font></b>  

# En el criptoanálisis, la técnica de análisis de frecuencias consiste en el aprovechamiento de estudios sobre la frecuencia de las letras o grupos de letras en los idiomas para poder establecer hipótesis para aprovecharlas para poder descifrar un texto cifrado sin tener la clave de descifrado (romper). Es un método típico para romper cifrados clásicos. 
# Haz una anailisis de frecuencias para los 10 primeros càpitulos de el libro "el_quijote.txt"

# In[48]:


def upperCase(entrada):
    entrada = entrada.upper()
    return entrada

def normalize(entrada):
    entrada = upperCase(entrada)
    replacements = (("Á", "A"),("Á","A"),("É", "E"),("É", "E"),
                    ("Í", "I"),("Í", "I"),("Ó", "O"),("Ó", "O"),
                    ("Ú", "U"),("?",""),("¿",""),(",",""),(".",""),
                    (":",""),(";",""),("(",""),(")",""))
    for a, b in replacements:
        entrada = entrada.replace(a, b).replace(a.upper(), b.upper())
    return entrada


abc="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
archivo="el_quijote.txt"
f = open("el_quijote.txt", "r").readlines()
text = ""
for line in f:
    text += line
    
cantidades = []

text = normalize(text)

for letra in abc:
    cantidades.append(text.count(letra))


print(cantidades)
    

i = 0
for letra in abc:
    print("%.2c  " % letra,"%.3f" % (cantidades[i]/len(text)))
    i+=1


# Salida:
# 
#     A   0.127
#     B   0.017
#     C   0.037
#     D   0.053
#     E   0.138
#     F   0.005
#     .
#     .
#     .

# <hr>
# <b><font color='red'>Ejercicio 06</font></b>  

# haz un analisis de frecuencias en el libro encriptado "RH RQCEPESEFM AR MPM.txt", y comparala con el analisis anterior
# 

# In[62]:


abc="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
f = open("el_quijote.txt", "r").readlines()
f2 = open("RH RQCEPESEFM AR MPM.txt", "r").readlines()

def upperCase(entrada):
    entrada = entrada.upper()
    return entrada

def normalize(entrada):
    entrada = upperCase(entrada)
    replacements = (("Á", "A"),("Á","A"),("É", "E"),("É", "E"),
                    ("Í", "I"),("Í", "I"),("Ó", "O"),("Ó", "O"),
                    ("Ú", "U"),("?",""),("¿",""),(",",""),(".",""),
                    (":",""),(";",""),("(",""),(")",""))
    for a, b in replacements:
        entrada = entrada.replace(a, b).replace(a.upper(), b.upper())
    return entrada




textQuijote = ""
textEncriptado = ""
for line in f:
    textQuijote += line
for line in f2:
    textEncriptado += line

cantidadesQuijote = []
cantidadesEncriptado = []

textQuijote = normalize(textQuijote)
textEncriptado = normalize(textEncriptado)

for letra in abc:
    cantidadesQuijote.append(textQuijote.count(letra))

for letra in abc:
    cantidadesEncriptado.append(textEncriptado.count(letra))


    
print("el_quijote.txt","  RH RQCEPESEFM AR MPM.txt")   
i = 0
for letra in abc:
    print("%.2c  " % letra,"%.3f" % (cantidadesQuijote[i]/len(textQuijote)),"       %.2c  " % letra,"%.3f" % (cantidadesEncriptado[i]/len(textEncriptado)))
    i += 1


# salida:
# 
#     el_quijote.txt   RH RQCEPESEFM AR MPM.txt
#     A   0.127        A   0.049
#     B   0.017        B   0.006
#     C   0.037        C   0.040
#     .                .
#     .                .
#     .                .

# <hr>
# <b><font color='red'>Ejercicio 07</font></b>  
# 
# Comparando el analisis de frecuencia sabrías decir cual es el titulo del libro.  
# RH RQCEPESEFM AR MPM

# 

# In[ ]:




