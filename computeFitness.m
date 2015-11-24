function [fitnesses]=calculaFitness(A)
javaaddpath([pwd '\SemaphoreSimulator.jar'])
%javaclasspath
fitnesses = zeros(size(A,3),1); %Vector columnaaa

for i=1:size(A,3)
    fitnesses(i) = Control.SemaphoreSimulator.maine(A(:,:,i));
end
end