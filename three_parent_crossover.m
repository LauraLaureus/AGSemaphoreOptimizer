function [Pop] = three_parent_crossover(Selection,mask1,mask2,mask3)
% generar la permutacion de los padres
perm = randperm(size(Selection,3));
% si el num de padres no es multiplo de 2 añadir el número que sea
% necesario


%gen tWO parent table
two_parent_table = vec2mat(perm,3);
%third_parent_vector = find_third_parent(two_parent_table);


Pop = zeros(size(Selection,1),size(Selection,2),round(3*size(Selection,3)/2));

for i=1:size(two_parent_table,1)
    
    Pop(:,:,3*(i-1)+1) = apply_mask(mask1,Selection(:,:,two_parent_table(i,1)),Selection(:,:,two_parent_table(i,2)),Selection(:,:,two_parent_table(i)));
    Pop(:,:,3*(i-1)+2) = apply_mask(mask2,Selection(:,:,two_parent_table(i,1)),Selection(:,:,two_parent_table(i,2)),Selection(:,:,two_parent_table(i)));
    Pop(:,:,3*(i-1)+3) = apply_mask(mask3,Selection(:,:,two_parent_table(i,1)),Selection(:,:,two_parent_table(i,2)),Selection(:,:,two_parent_table(i)));
end
end