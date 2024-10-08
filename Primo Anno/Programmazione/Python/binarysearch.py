#algoritmo di ricerca binaria per liste ordinate
#il programma restiuisce TRUE se il numero inserito si trova nella lista, altrimenti FALSE


def binary_search(item_list,item):
	
	first = 0
	last = len(item_list)-1
	
	found = False
	
	while( first<=last and not found):
		
		mid = (first + last)//2
		
		if item_list[mid] == item :
			found = True
		else:
			if item < item_list[mid]:
				last = mid - 1
			else:
				first = mid + 1	
	return found
	
print(binary_search([1,2,3,5,8,9,12,13,15,61,71,81,91,123,51513], 51513))
print(binary_search([1,2,3,5,8], 5))




