function [max_fitnesses,mean_fitnesses] = genetic_algoritm(siz,probability_selection,p_mutation)
%%Devuelve como max, un array con los valores máximos de bondad de cada
%%generacion, y como mean, un array con los valores medios de bondad.
%%Recibe como primer parámetro el sizaño de la población, la probabilidad
%%para el método de selección (método de selección por torneo
%%probabilistico) y el número de iteraciones.

max_fitnesses = [];
mean_fitnesses =[];
javaaddpath([pwd '\SemaphoreSimulator.jar'])
sim = javaObject('Control.SimulatorFactory');

if(mod(siz,3) ~= 0)
    siz = siz + (3- mod(siz,3));
end

Pop = round(rand(12,4,siz));
[mask1,mask2,mask3]=generate_mask_hijos();

for i=1:siz
    disp('Time for fitness')
    tic;fitnesses = computeFitness(Pop,sim);toc;
    disp('Time for selection')
    tic;seleccion = probabilistic_tourneau(fitnesses,Pop,probability_selection);toc;
    disp('Time for crossover')
    tic;Pop = three_parent_crossover(seleccion,mask1,mask2,mask3);toc;
    disp('Time for mutation')
    tic;Pop = mutation(p_mutation,Pop);toc;
    
    max_fitnesses=[ max_fitnesses max(fitnesses)];
    mean_fitnesses = [mean_fitnesses mean(fitnesses)];
end;


end