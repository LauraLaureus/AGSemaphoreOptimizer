function [seleccion] = seleccion_por_torneo_probabilistico(fitnesses,Pop,p_seleccion)

seleccion = zeros(size(Pop,1),size(Pop,2),size(Pop,3));
availables = 1:size(Pop,3);
for i=1:size(Pop,3)
    boletos_elegidos = sort(randperm(size(availables,2),2)); %selecciona 2
   
    max_elegidos = 0;
    min_elegidos = 0;
    if fitnesses(availables(boletos_elegidos(1))) > fitnesses(availables(boletos_elegidos(2)))
        max_elegidos = availables(boletos_elegidos(1));
        min_elegidos = availables(boletos_elegidos(2));
    else
        max_elegidos = availables(boletos_elegidos(2));
        min_elegidos = availables(boletos_elegidos(1));
    end

    if rand(1) >= p_seleccion
        seleccion(:,:,i) = Pop(:,:,max_elegidos);
    else
        seleccion(:,:,i) = Pop(:,:,min_elegidos);
    end
end

end