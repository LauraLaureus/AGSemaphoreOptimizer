function [fitnesses]=calculaFitness(A,simulator)
fitnesses = zeros(size(A,3),1); %Vector columnaaa

for i=1:size(A,3)
    %disp('Tiempo de simulacion');
    %tic;
    fitnesses(i) = simulator.simulate(A(:,:,i));
    %toc;
end
end